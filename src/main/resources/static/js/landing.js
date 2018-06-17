$(function() {
    $('section01').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({ scrollTop: $($(this).attr('href')).offset().top}, 500, 'linear');
    });
});
$(document).ready(function() {
    $.ajax({
        url: "/api/v1/article/1"
    }).then(function(data) {
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        console.log(data)
    });
});

document.addEventListener('DOMContentLoaded', function () {

    // Get all "navbar-burger" elements
    var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    // Check if there are any navbar burgers
    if ($navbarBurgers.length > 0) {

        // Add a click event on each of them
        $navbarBurgers.forEach(function ($el) {
            $el.addEventListener('click', function () {

                // Get the target from the "data-target" attribute
                var target = $el.dataset.target;
                var $target = document.getElementById(target);

                // Toggle the class on both the "navbar-burger" and the "navbar-menu"
                $el.classList.toggle('is-active');
                $target.classList.toggle('is-active');

            });
        });
    }

});


//from here this will be display articles
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/v1/article/latest",
        cache: true,
        timeout: 100000,
        success: function (data) {

            // console.log(data);



            data.forEach(function (element){
                console.log(element)

                var card_div = document.createElement("div");
                card_div.setAttribute("class", "column");
                card_href="/article/"+element.id;
                clean_title= element.title.replace(/["']/g, "");

              // var content=decodeURIComponent(element.content.substring(0,50));
                card_div.innerHTML=`<div class="column">
            <div class="card">

                <div class="card-content">
                    <div class="content">
                        ${clean_title}
                   </div>
                  
                </div>
                <footer class="card-footer is-centered">
                    <a href=${card_href} class="card-footer-item">Read More</a>
                </footer>
            </div>
        </div>`;

                document.getElementById("add_cards").appendChild(card_div);



            });



        },
        error: function (e) {



        }
    });
});
