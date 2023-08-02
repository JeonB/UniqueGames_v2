$(document).ready(function () {
    /**
     * 댓글 작성 버튼 이벤트
     */
    $('button[name="cmtWrite"]').on("click", function () {
        let url = window.location.href;
        let login = document.getElementById("member-id").value;
        if (login != "") {
            commentWrite(url);

        } else {
            if (confirm("로그인 후 이용 가능합니다. 로그인 하시겠습니까?")) {
                location.href = "/login";

            } else {

                return false;
            }

        }
    })

})

/**
 * 댓글 작성 처리 함수
 */
function commentWrite(url) {
    if ($("#form-control").val() != "" && $("#form-control").val().length <= 50) {
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

    } else if ($("#form-control").val().length > 50) {
        alert("50자 이내로 입력해주세요.")

        return false
    } else {
        alert("댓글 내용을 입력하세요.");

        return false
    }
}

/**
 * 댓글 삭제 처리 함수
 * @param commentId 댓글 id
 */
function commentDelete(commentId) {
    if (confirm('댓글을 삭제하시겠습니까?')) {
        let url = window.location.href;

        $.ajax({
            url     : "/comment-delete",
            data    : {
                no: commentId
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

/**
 * 댓글 신고 함수
 * @param commentId 댓글 번호
 */
function commentReport(commentId) {
    const login = document.getElementById("member-id").value;
    if (login != "") {
        sessionStorage.setItem("data", commentId)
        let popUp = showPopup();

    } else {
        if (confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
            location.href = "/login";
        } else {

            return false;
        }
    }
}

/**
 * alert 중복 방지를 위한 스크립트
 * @param result
 */
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
 * 신고 팝업
 */
function showPopup() {
    return window.open('/notice/popUp', "popup", "width=563, height=594, left=650, top=250");
}

/**
 * 초기화
 */
function init() {
    const commentId = sessionStorage.getItem("data")
    $.ajax({
        url     : "/popUpInit",
        data    : {"commentId": commentId},
        async   : true,
        cache   : false,
        type    : "POST",
        dataType: "JSON",
        success : function (result) {
            const data = result.cmtResult;
            if (data === "OK") {
                alert("이미 신고 처리된 댓글입니다.");
                window.close();
            } else {
                $(".report_nick").text(data.mid)
                $(".report_cont").text(data.commentContent)
            }
        },
        error   : function () {
            alert("비정상적인 접근입니다.");
            window.close();
        }
    });
}

/**
 * 신고 처리 함수
 */
function report() {
    let selectedId = $("input[name='select']:checked").attr('id');
    let reason = $("label[for='" + selectedId + "']").text()

    const data = {"id": sessionStorage.getItem("data"), "reason": reason};
    $.ajax({
        url        : "/reportSend",
        type       : "PUT",
        data       : JSON.stringify(data),
        dataType   : 'text',
        contentType: "application/JSON; charset=UTF-8",
        async      : true,
        cache      : false,
        success    : function (result) {
            console.log(result);
        },
        error      : function (xhr, status, error) {
            console.log("오류가 발생했습니다.");
            console.log("상태 코드:", xhr.status);
            console.log("오류 메시지:", error);
        },
    });
    alert("신고되었습니다. 관리자가 확인 후 신속히 처리하겠습니다.");
    window.close();

}

/**
 * 신고 처리 취소 함수
 * @param id 댓글 번호
 */
function reportCancel(id) {
    const url = window.location.href;
    $.ajax({
        url     : "/reportCancel",
        type    : "PUT",
        data    : {id: id},
        dataType: "text",
        async   : true,
        cache   : false,
        success : (result) => {
            if (result === "SUCCESS")
                window.location.replace(url);
            else {
                alert("oops, something went wrong...")
            }
        },
        error   : function (xhr, status, error) {
            console.log("오류가 발생했습니다.");
            console.log("상태 코드:", xhr.status);
            console.log("오류 메시지:", error);
        },
    })
}