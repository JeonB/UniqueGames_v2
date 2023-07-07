$(document).ready(function () {
    $("#a-memberlist").css("background", "linear-gradient(to right,#682CAD,#2FC0CC)").css("color", "white");
    hideDiv();
    $("#fra-donation-memberlist").css("display", "inline-block");

    $("a").click(function () {
        let id = $(this).attr("id");
        $("a").css("background", "none").css("color", "black");
        $(this).css("background", "linear-gradient(to right,#682CAD,#2FC0CC)").css("color", "white");

        if (id = "a-memberlist") {
            hideDiv();
            $("#fra-donation-memberlist").css("display", "inline-block");
        } else if (id = "a-gamelist") {
            hideDiv();
            $("#fra-donation-gamelist").css("display", "inline-block");
        } else if (id = "a-gameregister") {
            hideDiv();
            $("#fra-donation-gameregister").css("display", "inline-block");
        } else if (id = "a-donation") {
            hideDiv();
            $("#fra-donation-donation").css("display", "inline-block");
        }
    });

    function hideDiv() {
        $("#fra-donation-memberlist").css("display", "none");
        $("#fra-donation-gamelist").css("display", "none");
        $("#fra-donation-gameregister").css("display", "none");
        $("#fra-donation-donation").css("display", "none");
    }
});