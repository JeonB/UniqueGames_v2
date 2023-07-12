$(document).ready(function () {
    setButtonColor();

    // sidebar button color
    function setButtonColor() {
        let id = $("#admin-type").val();

        $("a").css("background", "none").css("color", "black");
        $("#" + id).css("background", "linear-gradient(to right,#682CAD,#2FC0CC)").css("color", "white");

        if (id == "a-memberlist") {
            $("#btn-member-personal").css("background", "white").css("color", "rgba(57, 57, 57, 1)");
        }
    }

    // personal - company
    $('button[name = "btn-member"]').click(function () {
        $('button[name = "btn-member"]').css("background", "rgba(233, 233, 233, 1)").css("color", "rgba(110, 110, 110, 1)");
        $(this).css("background", "white").css("color", "rgba(57, 57, 57, 1)");
        $("#table-type").val($(this).val());
    });

    // show modal
    $("#btn-fk").click(function () {
        $("#modal-admin").css("display", "inline-block");
    });

    // hide modal
    $("#btn-modal").click(function () {
        $("#modal-admin").css("display", "none");
    });

    // go url page
    $("#btn-url").click(function () {
        let url = $("#url").val();
        window.open("http://localhost:9000" + url);
    });
});