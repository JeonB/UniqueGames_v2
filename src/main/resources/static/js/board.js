$(document).ready(function () {

    /**
     * list 버튼 이벤트
     */
    // 전체 삭제 버튼 이벤트
    $('button[name="listDeleteAll"]').on("click", function () {

        let list = document.getElementsByName("list")

        if (list[0] != null) {
            if (confirm("모든 게시글을 삭제하시겠습니까?")) {
                list.forEach(e => {
                    e.checked = true
                })
                boardManage();

            }
            ;
        } else {
            alert("게시글이 존재하지 않습니다.");

            return false;
        }
    });

    // 삭제 버튼 이벤트
    $('button[name="listDelete"]').on("click", function () {
        let checked = $("input[name='list']:checked").get();

        if (checked.length == 0) {
            alert("선택된 게시글이 없습니다.");

            return false;
        } else {
            if (confirm("정말로 삭제하시겠습니까?")) {
                boardManage();

            }
        }
    });

    // 수정 버튼 이벤트
    $('button[name="listUpdate"]').on("click", function () {
        let checked = $("input[name='list']:checked").get();

        if (checked.length == 0) {
            alert("선택된 게시글이 없습니다.");

            return false;
        } else {
            if (checked.length > 1) {
                alert("게시글을 하나만 선택하세요.");
                $("input[name='list']").prop("checked", false);

                return false;
            } else {
                location.href = "notice/update/" + $(checked).val();
            }
        }
    });

    // 작성 버튼 이벤트
    $('button[name="listWrite"]').on("click", function () {

        location.href = "/notice/write";
    });

    // 검색 버튼 이벤트
    $("#btn-search").on("click", function () {
        searchScript();

    })

    $('input[name="q"]').on("keydown", function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            searchScript();
        }
    })

    // 목록 버튼 이벤트
    $('button[name="getList"]').on("click", function () {

        location.href = "/notice/list";
    })

    /**
     * write 버튼 이벤트
     */
    // 작성 버튼 이벤트
    $('button[name="write"]').on("click", function () {
        if ($("input[name='title']").val() == "") {
            alert("제목을 입력해주세요.");
            $("input[name='title']").focus();

            return false;
        } else if ($("input[name='title']").val().length > 50) {
            alert("제목이 너무 깁니다. 제목은 50자 이내로 작성해주세요.");
            $("input[name='title']").focus();

            return false;
        } else if ($("textarea[name='content']").val().length > 3000) {
            alert("내용이 너무 깁니다. 내용은 3000자 이내로 작성해주세요.");
            $("textarea[name='content']").focus();

            return false;
        } else {
            const htmlString = editor.getData()
            // 정규 표현식으로 이미지 태그에서 파일 이름 추출
            const imgTagRegex = /<img\s+src="([^"]+)">/g
            const matches = htmlString.matchAll(imgTagRegex)

            // 추출된 파일 이름 출력
            for (const match of matches) {
                const srcAttr = match[1]
                const fileName = srcAttr.substring(srcAttr.lastIndexOf('/') + 1)
                const input = document.createElement("input")
                input.type = "hidden"
                input.name = "uploadImg"
                input.value = fileName
                document.getElementById("fileBox").appendChild(input)
            }
            writeForm.submit()
        }

    });

    // 취소 버튼 이벤트
    $('button[name="cancel"]').on("click", function () {
        const htmlString = editor.getData()
        // 정규 표현식으로 이미지 태그에서 파일 이름 추출
        const imgTagRegex = /<img\s+src="([^"]+)">/g
        const matches = htmlString.matchAll(imgTagRegex)

        let deleteImgArray = [];
        for (const match of matches) {
            const srcAttr = match[1]
            const fileName = srcAttr.substring(srcAttr.lastIndexOf('/') + 1)
            deleteImgArray.push(fileName);
        }

        $.ajax({
            url        : "/imgDelete",
            type       : "DELETE",
            data       : {deleteImgArray: deleteImgArray},
            dataType   : "text",
            traditional: true,
            async      : true,
            cache      : false,
            success    : () => {
                console.log("ok")
            },
            error      : () => {
                console.log("fail")
            }

        })

        const URLSearch = new URLSearchParams(location.search);
        if (window.location.href.indexOf("update") > -1) {
            location.href = "notice-content?stat=up&no=" + URLSearch.get('no');
        } else {
            location.href = "/notice/list";
        }
    });

    /**
     * content 버튼 이벤트
     */
    // 수정 버튼 이벤트
    $('button[name="update"]').on("click", function () {
        let id = document.getElementById("authorId").dataset.authorId;
        let login = document.getElementById("member-id").value;
        let no = $("input[name='postId']").val();

        if (id == login) {
            location.href = "/notice/write/up/" + no;

        } else {
            alert("권한이 없습니다.");

            return false;
        }
    })

    // 삭제 버튼 이벤트
    $('button[name="delete"]').on("click", function () {
        let id = document.getElementById("authorId").dataset.authorId;
        let login = document.getElementById("member-id").value;
        let no = $("input[name='postId']").val();

        if (id == login) {
            if (confirm("정말로 삭제하시겠습니까?")) {
                noticeDelete.submit();
            }

        } else {
            alert("권한이 없습니다.");

            return false;
        }
    })

    // 목록 버튼 이벤트
    $('button[name="list"]').on("click", function () {
        location.href = "/notice/list";
    })

});

/**
 * 게시글 목록 삭제 함수
 */
function boardManage() {
    const url = window.location.href
    $.ajax({
        url    : "/board-manage",
        data   : $('form[name="boardManage"]').serialize(),
        async  : true,
        cache  : false,
        type   : "delete",
        success: function (result) {
            if (result == "SUCCESS") {
                alert("작업 성공")
            } else {
                alert("작업 실패")
            }

            window.location.replace(url)
        },
        error() {
            alert("지금은 시도할 수 없습니다.\n상태가 지속될 경우 관리자에게 문의하세요.");
        }
    })
}

/**
 * 검색 함수
 */
function searchScript() {
    if ($('input[name="q"]').val() == "") {
        alert("검색어를 입력하세요");

        return false;
    } else {

        boardSearch.submit();
    }
}

/**
 * alert 다중 실행 방지를 위한 함수
 * @param result 결과
 */
function getResult(result) {
    if (result == 'insuccess') {
        alert("게시글이 성공적으로 등록되었습니다.");
    }

    if (result == 'upsuccess') {
        alert("수정되었습니다.");
    }

    if (result == 'complete') {
        alert("삭제되었습니다.");
    }

    if (result == 'fail') {
        alert("작업에 실패했습니다.\n잠시후에 다시 시도해주세요.");
    }

    history.replaceState({}, null, null);
}

/**
 * 페이징 처리 함수
 * @param pageCount 전체 페이지 수
 * @param dbCount DB에서 가져온 전체 행수
 * @param page 요청한 페이지
 * @param pageSize 한페이지당 게시물 수
 */
function getPagination(pageCount, dbCount, page, pageSize) {
    var pager = jQuery('#ampaginationsm').pagination({
        maxSize : pageCount,              // max page size
        totals  : dbCount,      // total pages
        page    : page,        // initial page
        pageSize: pageSize,          // max number items per page

        // custom labels
        lastText : '&raquo;&raquo;',
        firstText: '&laquo;&laquo;',
        prevText : '&laquo;',
        nextText : '&raquo;',

        btnSize: 'lg'    // 'sm'  or 'lg'
    });

    jQuery('#ampaginationsm').on('am.pagination.change', function (e) {
        jQuery('.showlabelsm').text('The selected page no: ' + e.page);
        if (location.href.includes("search")) {
            const urlParams = new URLSearchParams(location.search)
            $(location).attr('href', "/notice/list/search?q=" + urlParams.get("q") + "&page=" + e.page)
        } else {
            $(location).attr('href', "/notice/list/" + e.page);
        }
    });
}