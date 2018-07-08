$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/v1/article/archives",
        cache: true,
        timeout: 100000,
        success: function (data) {

            // console.log(data);



            data.forEach(function (element){

            var tr_year = document.createElement("tr");
            year_href="/"+element.year;
            tr_year.innerHTML=`
                <td>
                     <a href="${year_href}">
                <h1 class="title is-4 is-primary">${element.year}</h1>
                      </a> 
                </td>
            `;

                 document.getElementById("article-body").appendChild(tr_year);


                var arts = $.parseJSON(element.articleArray);

                arts.forEach(function(ets){
                    clean_title = ets.title.substring(1, ets.title.length-1);
                    var tr_arts = document.createElement("tr");
                    art_href="article/"+ets.id;

                    var month_name = new Date(ets.createDate);
                    locale="en-us";
                 var   month = month_name.toLocaleDateString(locale,{month:"long"});
                 tr_arts.innerHTML=`
                 <td> <a class="is-link" href=${art_href}>${month}</a></td>
                    <td> <a class="is-link" href=${art_href}>${clean_title}</a></td>
                 </td>
            `;
                    document.getElementById("article-body").appendChild(tr_arts);

                });




                //Create a tr element and append the tag element over thee loop
               //console.log(element);
               //  var  article_href="/article/"+element.id;
               //  var art_ele = document.createElement("tr");
               //
               //  //get the date and convert to day
               //  var raw_date = new Date(element.createdate);
               //  var locale = "en-us";
               //  var month = raw_date.toLocaleString(locale, {month: "long"});
            //
            //
            //     art_ele.innerHTML=`
            //   <td> <a class="is-link" href=${article_href}>${month}</a></td>
            //         <td> <a class="is-link" href=${article_href}>${element.title}</a></td>
            // `;
            //     console.log(art_ele);
            //     document.getElementById('')


            });



        },
        error: function (e) {



        }
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
