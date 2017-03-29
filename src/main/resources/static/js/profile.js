/**
 * Created by wchen00 on 3/8/17.
 */

var btn = false
$(document).ready(function () {
    initOwl();
    initAction();

    //initMap();
    //$("#loginViaBtn, #loginOnlyBtn,#signupBtn").on("mouseenter", function () {
    //    if (btn == false) {
    //        $("#loginOnlyBtn").hide().html("<a href=\"/signin/linkedin\" class=\"smoothScroll btn btn-default\">Login Only</a>").fadeIn("slow");
    //
    //        $("#signupBtn").hide().html("<a  href=\"/signup/linkedin\" class=\"smoothScroll btn btn-default\">SignUp as admin</a>").delay(100).fadeIn("slow");
    //        btn = true
    //    }
    //})
    //$("#linkedinBtnContainer").on("mouseleave", function () {
    //    $("#loginOnlyBtn,#signupBtn").hide();
    //    btn = false;
    //});

});
function initAction() {
    $("#owl-demo").on("mouseover", ".jd", function () {

            var job_index = $(this).parent().parent().parent().attr("job-index");
            var id = "popUp" + job_index;
            var popup = document.getElementById(id);
            var job_details = $(this).attr("jd-detail-val");
            var list;
            var text = "";
            if (job_details) {
                list = job_details.split("####");
                for (var i = 0; i < list.length; i++) {
                    if (i < list.length - 1)
                        text += " - " + list[i] + "\n";
                    else {
                        text += " - " + list[i];
                    }
                }
            }
            $('#' + id).html(text);
            popup.classList.toggle("show");
        }
    );

    $("#owl-demo").on("mouseleave", ".jd", function () {
        var job_index = $(this).parent().parent().parent().attr("job-index");
        var id = "popUp" + job_index;
        var popup = document.getElementById(id);
        popup.classList.toggle("show");
    });
}
function initOwl() {
    var theme_slider = $("#owl-demo");
    $("#owl-demo").owlCarousel({
        navigation: false,
        slideSpeed: 300,
        paginationSpeed: 400,
        autoPlay: 20000,
        stopOnHover: true,
        addClassActive: true,
        // transitionStyle: "fade",
        singleItem: true
    });
    //$("#owl-demo2").owlCarousel({
    //    slideSpeed: 300,
    //    autoPlay: true,
    //    navigation: true,
    //    navigationText: ["&#xf007;", "&#xf006;"],
    //    pagination: false,
    //    singleItem: true
    //});

    // Custom Navigation Events
    $(".next-arrow").click(function () {
        theme_slider.trigger('owl.next');
    })
    $(".prev-arrow").click(function () {
        theme_slider.trigger('owl.prev');
    })
}