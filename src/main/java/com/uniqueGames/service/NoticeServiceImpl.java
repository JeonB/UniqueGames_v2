package com.uniqueGames.service;


import com.uniqueGames.fileutil.BoardUtil;
import com.uniqueGames.fileutil.FileUploadUtil;
import com.uniqueGames.model.Notice;
import com.uniqueGames.repository.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class NoticeServiceImpl extends FileUploadUtil implements NoticeService {

    private NoticeMapper noticeMapper;
    private BoardUtil boardUtil;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper, BoardUtil boardUtil) {
        this.noticeMapper = noticeMapper;
        this.boardUtil = boardUtil;
    }

    @Override
    protected void extractFile(Object obj) {
        super.setFile(((Notice) obj).getFile());
    }

    /**
     * ㅋ
     * 공지사항 - 전체 리스트 조회
     */
    @Override
    public List<Notice> getNoticeList(int startCount, int endCount) {
        return boardUtil.getOutput(noticeMapper.selectNotice(startCount, endCount));
    }

    /**
     * 공지사항 - 상세 보기
     */
    @Override
    public Notice getNoticeContent(String stat, String no) {
        Notice notice = noticeMapper.selectContent(no);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (notice != null) {
            if (stat == null || stat.equals("")) {
                noticeMapper.hitsCount(no);
                notice.setNoticeHits(notice.getNoticeHits() + 1);
            }

            notice.setDateOutput(format.format(notice.getNoticeDate()));

        }

        return notice;
    }

    /**
     * 공지사항 작성
     *
     * @param notice
     * @return
     */
    @Override
    public int insert(Notice notice) {
        notice.setImageId(fileCheck(notice));
        int insResult = noticeMapper.insertNotice(notice);
        if (notice.getImageId() != null) {
            noticeMapper.insertFile(notice);
            super.fileSave();
        }

        return insResult;
    }

    /**
     * 공지사항 - 수정
     */
    @Override
    public int update(Notice notice) {
        String[] fileResult = fileUpdate(notice, notice.getImageId());
        notice.setImageId(fileResult[0]);

        int result = noticeMapper.updateNotice(notice);
        if (notice.getFile() != null && !notice.getFile().isEmpty()) {

            if (noticeMapper.fileCheck(notice) == 1) {
                noticeMapper.updateFile(notice);

            } else {
                noticeMapper.updateUploadFile(notice);

            }

        } else if (!fileResult[1].equals("") && fileResult[1].equals("delete")) {

            noticeMapper.deleteFile(notice);
        }

        return result;
    }

    /**
     * 공지사항 - 삭제
     */
    @Override
    public int delete(String no, String imgDel) {

        fileDelete(imgDel);

        return noticeMapper.deleteNotice(no);
    }

    /**
     * 공지사항 리스트 삭제
     *
     * @param list
     * @return
     */
    @Override
    public int deleteList(String[] list) {
        fileListDelete(noticeMapper.deleteListBefore(list));

        return noticeMapper.deleteList(list);
    }

    /**
     * 공지사항 - 검색
     */
    @Override
    public List<Notice> search(String keyword, int startCount, int endCount) {

        return boardUtil.getOutput(noticeMapper.searchList(keyword, startCount, endCount));
    }

}
