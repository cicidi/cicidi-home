/**
 * Created by cicidi on 2/17/2017.
 */

$(document).ready(function () {
    //initAction();
    //initMap();
    var btn = false
    $("#loginViaBtn, #loginOnlyBtn,#signupBtn").on("mouseover", function () {
        if (btn == false) {
            //$("#loginOnlyBtn").hide().html("<a href=\"/signin/linkedin\" class=\"smoothScroll btn btn-default\">Login Only</a>").fadeIn("slow");
            //
            //$("#signupBtn").hide().html("<a  href=\"/signup/linkedin\" class=\"smoothScroll btn btn-default\">SignUp as admin</a>").delay(100).fadeIn("slow");
            $("#loginOnlyBtn").fadeIn("slow");
            $("#signupBtn").fadeIn("slow");
            btn = true;
        }
    })
    $("#linkedinBtnContainer").on("mouseleave", function () {
        $("#loginOnlyBtn,#signupBtn").hide();
        btn = false;
    });
})

//function initAction() {
//    $("#owl-demo").on("mouseover", ".jd", function () {
//
//            var job_index = $(this).parent().parent().parent().attr("job-index");
//            var id = "popUp" + job_index;
//            var popup = document.getElementById(id);
//            var job_details = $(this).attr("jd-detail-val");
//            var list;
//            var text = "";
//            if (job_details) {
//                list = job_details.split("####");
//                for (var i = 0; i < list.length; i++) {
//                    if (i < list.length - 1)
//                        text += " - " + list[i] + "\n";
//                    else {
//                        text += " - " + list[i];
//                    }
//                }
//            }
//            $('#' + id).html(text);
//            popup.classList.toggle("show");
//        }
//    );
//
//    $("#owl-demo").on("mouseleave", ".jd", function () {
//        var job_index = $(this).parent().parent().parent().attr("job-index");
//        var id = "popUp" + job_index;
//        var popup = document.getElementById(id);
//        popup.classList.toggle("show");
//    });

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
//}
//function myFunction() {
//    var popup = document.getElementById("myPopup");
//    popup.classList.toggle("show");
//}
