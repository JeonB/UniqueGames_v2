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
        } else if ($("textarea[name='content']").val().length > 300) {
            alert("내용이 너무 깁니다. 내용은 300자 이내로 작성해주세요.");
            $("textarea[name='content']").focus();

            return false;
        } else {
            writeForm.submit();

        }

    });

    // 취소 버튼 이벤트
    $('button[name="cancel"]').on("click", function () {
        const URLSearch = new URLSearchParams(location.search);
        if (window.location.href.indexOf("update") > -1) {
            location.href = "notice-content?stat=up&no=" + URLSearch.get('no');
        } else {
            location.href = "/notice/list";
        }
    });

    // 파일 버튼 이벤트
    $('button[name="file"]').on("click", function () {
        $('#upload-hidden').click();
    });

    // file
    $('#upload-hidden').on('change', function () {
        let file = $("#upload-hidden")[0].files[0];
        let fileName = file.name;
        let fileType = file.type;
        let hidden = document.getElementById("upload-hidden");
        let name = document.getElementById("upload-name");

        if (fileType.startsWith("image/")) {
            $('#upload-name').val(file.name);

            let output = "<button type='button' class='btn-style' name='filedel'>취소</button>";
            $('#fileDelbtn').html(output);
            $(".filebox").css("margin-right", "78px");
            // 파일 취소 버튼 이벤트
            $('.filebox button[name="filedel"]').on("click", function () {
                hidden.value = null;
                name.value = "";
                $('button[name="filedel"]').remove();
                $("#filebox").css("margin-right", "164px");
            });

        } else {
            alert("이미지만 선택 가능합니다.");
            $('#upload-hidden').val(null);
            $('#upload-name').val("");
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

    // 댓글 작성 이벤트
    $('button[name="cmtWrite"]').on("click", function () {
        let url = window.location.href;
        let login = document.getElementById("member-id").value;
        if (login != "") {
            if ($("#form-control").val() != "") {
                $.ajax({
                    url     : "/commentWriteProc",
                    data    : $("#comment-write").serialize(),
                    dataType: "text",
                    async   : true,
                    Cache   : false,
                    type    : "POST",
                    success : function (result) {
                        if (result == "SUCCESS") {
                            alert("댓글이 등록되었습니다.");
                            window.location.replace(url);
                        } else {
                            alert("댓글 등록이 실패하였습니다.");
                        }
                    },
                    error   : function (xhr, status, error) {
                        alert("댓글 작성 실패. 관리자에게 문의하세요");
                    }
                });

            } else
                alert("댓글 내용을 입력하세요.");

        } else {
            if (confirm("로그인 후 이용 가능합니다. 로그인 하시겠습니까?")) {
                location.href = "login";

            } else {

                return false;
            }

        }
    })

});

// 댓글 삭제 이벤트
function commentDelete(commentId) {
    if (confirm('댓글을 삭제하시겠습니까?')) {
        let url = window.location.href;

        $.ajax({
            url     : "/comment-delete",
            data    : {
                no: commentId,
            },
            dataType: "text",
            type    : "DELETE",
            success : function (result) {
                if (result == "SUCCESS") {
                    alert("댓글이 삭제되었습니다.");
                } else {
                    alert("댓글 삭제가 실패하였습니다.");
                }
                window.location.replace(url);
            },
            error   : function (error) {
                alert("지금은 시도할 수 없습니다.\n상태가 지속될 경우 관리자에게 문의하세요.");
            }

        });
    } else {
        return false;
    }
}

// 게시글 목록 삭제 이벤트
function boardManage() {
    const url = window.location.href
    $.ajax({
        url    : "/board-manage",
        data   : $('form[name="boardManage"]').serialize(),
        async  : true,
        cache  : false,
        type   : "delete",
        success: function (result) {
            if (result == "SUCCESS")
                alert("작업 성공")
            else
                alert("작업 실패")
            window.location.replace(url)
        },
        error() {
            alert("지금은 시도할 수 없습니다.\n상태가 지속될 경우 관리자에게 문의하세요.");
        }
    })
}

// 검색 이벤트
function searchScript() {
    if ($('input[name="q"]').val() == "") {
        alert("검색어를 입력하세요");

        return false;
    } else {

        boardSearch.submit();
    }
}

// alert 중복 방지를 위한 스크립트
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

function getResultCmt(result) {
    if (result == 'success') {
        alert("댓글이 등록되었습니다.");
    }

    if (result == 'fail') {
        alert("작업에 실패했습니다.\n잠시후에 다시 시도해주세요.");
    }

    history.replaceState({}, null, null);
}

/**
 * 페이징 처리
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