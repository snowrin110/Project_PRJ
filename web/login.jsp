<%-- 
    Document   : login
    Created on : Nov 6, 2022, 2:49:24 PM
    Author     : rinsnow
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style1.css"/>
    </head>
    <body>
        <div class="cont">
            <div class="demo">
                <div class="login">
                    <div class="login__check"></div>
                    <div class="login__form">
                        <form action="login" method="post">
                            <div class="login__row">
                                <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
                                </svg>
                                <input type="text" class="login__input name" name="acc" placeholder="Username"/>
                            </div>
                            <div class="login__row">
                                <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
                                </svg>
                                <input type="password" class="login__input pass" name="pass" placeholder="Password"/>
                            </div>
                            <button type="submit" class="login__submit">Sign in</button>
                            <p class="login__signup">Don't have an account? &nbsp;<a>Sign up</a></p>
                        </form>
                    </div>
                </div>

                <script>
                    $(document).ready(function () {

                        var animating = false,
                                submitPhase1 = 1100,
                                submitPhase2 = 400,
                                logoutPhase1 = 800,
                                $login = $(".login"),
                                $app = $(".app");

                        function ripple(elem, e) {
                            $(".ripple").remove();
                            var elTop = elem.offset().top,
                                    elLeft = elem.offset().left,
                                    x = e.pageX - elLeft,
                                    y = e.pageY - elTop;
                            var $ripple = $("<div class='ripple'></div>");
                            $ripple.css({top: y, left: x});
                            elem.append($ripple);
                        }
                        ;

                        $(document).on("click", ".login__submit", function (e) {
                            if (animating)
                                return;
                            animating = true;
                            var that = this;
                            ripple($(that), e);
                            $(that).addClass("processing");
                            setTimeout(function () {
                                $(that).addClass("success");
                                setTimeout(function () {
                                    $app.show();
                                    $app.css("top");
                                    $app.addClass("active");
                                }, submitPhase2 - 70);
                                setTimeout(function () {
                                    $login.hide();
                                    $login.addClass("inactive");
                                    animating = false;
                                    $(that).removeClass("success processing");
                                }, submitPhase2);
                            }, submitPhase1);
                        });

                        $(document).on("click", ".app__logout", function (e) {
                            if (animating)
                                return;
                            $(".ripple").remove();
                            animating = true;
                            var that = this;
                            $(that).addClass("clicked");
                            setTimeout(function () {
                                $app.removeClass("active");
                                $login.show();
                                $login.css("top");
                                $login.removeClass("inactive");
                            }, logoutPhase1 - 120);
                            setTimeout(function () {
                                $app.hide();
                                animating = false;
                                $(that).removeClass("clicked");
                            }, logoutPhase1);
                        });

                    });
                </script>
                </body>
                </html>
