$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/v1/tag/article/count",
        cache: true,
        timeout: 100000,
        success: function (data) {

       // console.log(data);



        data.forEach(function (element){

            //Create a tr element and append the tag element over thee loop

          var  tag_href = "/tags/"+element.tagId;
            var tr_tag = document.createElement("tr");
           console.log(tag_href);

            tr_tag.innerHTML=`
              <td> <a class="is-link" href=${tag_href}>${element.tagName}</a></td>
                    <td> <a class="is-link" href=${tag_href}>${element.tagCount}</a></td>
            `;
            console.log(tr_tag);
            document.getElementById("tag_data").appendChild(tr_tag);
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
