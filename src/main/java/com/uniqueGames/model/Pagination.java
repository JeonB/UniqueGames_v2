package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

//    private CompuCategory category;
    private Integer page = 0;           //현재페이지
    private Integer perFirstRow = 0;    //한페이지의 첫번째 게시글
    private Integer perRows = 4;        //한페이지에 보여줄 게시글 수

    private Integer listStartNum;       //한페이지의 리스트 시작번호
    private Integer listEndNum;         //한페이지의 리스트 끝번호
    private Integer listLimit = 5;   //한페이지에 보여줄 리스트 수
    private Integer listLastNum;        //마지막페이지번호

    private String order = "createtime desc";

    private String searchText = "";
//    private String searchRange = "1";
    private String search = "";

    private Integer preview;
    private Integer next;


    public void setterProcess(){
        setPerFirstRow();
        setListStartNum();
        setListEndNum();
        setPreview();
        setNext();
    }


    /**
     * @param totalContentCount 전체 게시글 수
     *  마지막 페이지의 번호 설정하는 메소드
     */
    public void setListLastNum(double totalContentCount) {
        this.listLastNum = (int) Math.ceil(totalContentCount / this.perRows); //21개 게시글이 있으면 21/4 + 1 => 6개 페이지 그룹 생성
    }

    
    private void setPerFirstRow() {
        this.perFirstRow = (this.page-1)*this.perRows;
    }

    /**
     *  각 페이지의 첫번째 게시글 번호를 설정하는 메소드
     */
    private void setListStartNum() {
        //7페이지일때는 6~10페이지
        if(this.page % this.listLimit != 0){
            this.listStartNum = (this.page / this.listLimit) * this.listLimit +1;
        }else{
            this.listStartNum = this.page - (this.listLimit - 1);
        }
    }

    /**
     *  각 페이지의 마지막 게시글 번호를 설정하는 메소드
     */
    private void setListEndNum() {
        //3페이지가 끝인데 5페이지까지 보이면 안됨
        this.listEndNum = this.listStartNum + this.listLimit -1;
        if(this.listLastNum < this.listEndNum){
            this.listEndNum = this.listLastNum;
        }

    }

    private void setPreview() {
        this.preview = this.page -1;
    }

    private void setNext() {
        this.next = this.page +1;
    }

//    public void setSearch() {
//        String result = "";
//        String findRange = findRange(this.getSearchRange());
//
//        if(this.searchText.equals("")){ //검색값이 없을 때
//            if(this.category != null)
//                result = "where compucategory = '" + this.category + "'";
//        }else {  //검색값이 있을 때
//            boolean contains = findRange.contains(",");
//            if (contains == false) {
//                result = "where " + findRange + " like '%" + this.searchText + "%'";
//            } else {
//                String[] split = findRange.split(",");
//                result = "where (";
//                for (int i = 0; i < split.length; i++) {
//                    result = result + split[i] + " like '%" + searchText + "%'";
//                    if (i != (split.length - 1)) {
//                        result = result + " or ";
//                    } else {
//                        result = result + " )";
//                    }
//                }
//            }
//        }
//
//        this.search = result ;
//    }
//
//    private String findRange(String searchRange) {
//        switch (searchRange){
//            case "1" : return "compuname";
//            case "2" : return "writeremail";
//            case "3" : return "compudescription";
//            case "4" : return "compuname,compudescription";
//            default:return "compuname";
//        }
//    }
}
