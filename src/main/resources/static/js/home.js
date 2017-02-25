/**
 * Created by cicidi on 2/17/2017.
 */
$(document).ready(function () {
    initAction();

});
function initAction() {
    $("#owl-demo").on("mouseover", ".jd", function () {

        var job_index = $(this).parent().parent().parent().attr("job-index");
        var id = "popUp" + job_index;
        var popup = document.getElementById(id);
        var job_details = $(this).attr("jd-detail-val");
        var list = job_details.split("####");
        //var detail = "";
        //for (var i = 0; i < job_details.length; i++) {
        //    detail += job_details[i] + "\n"
        //}
        var text = "";
        for (var i = 0; i < list.length; i++) {
            text += " - " + list[i] + "\n";
        }
        $('#' + id).html(text);
        popup.classList.toggle("show");
    });

    $("#owl-demo").on("mouseleave", ".jd", function () {
        var job_index = $(this).parent().parent().parent().attr("job-index");
        var id = "popUp" + job_index;
        var popup = document.getElementById(id);
        popup.classList.toggle("show");
    });

    //$("#owl-demo").on("mouseover", ".jd", function () {
    //    var error = $(this).attr("data-error"); // Grab the error text from the clicked icon
    //    $("#viewError").text(error); // Put the error text into the modal popup
    //    $("#errorSpeedbump").modal({ // display the modal
    //        minHeight: 200,
    //        minWidth: 350,
    //        maxHeight: 400,
    //        maxWidth: 500,
    //        opacity: 30
    //    });
    //});
}
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}