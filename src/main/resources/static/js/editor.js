$( document ).ready(function() {

    console.log("initialing summernot");

    $('#summernote').summernote({
        placeholder: 'Hi Sandeep, Write something today',

        tabsize: 2,
        codemirror: { // codemirror options
            theme: 'monokai'
        },
        height: 650,


        disableResizeEditor: true
    });
});


// var save = function() {
//     var markup = $('#summernote').summernote('code');
//     console.log(markup);
//     $('#summernote').summernote('insertText', 'hello world');
// };

$("#submit").on('click', function() {

    var title = document.getElementById('article-title').value;

    var content = $('#summernote').summernote('code');


    // article object
    var article = {
        "title": title,
        "article": content


    }

    console.log(article);

});



$('#tagline').on('focus', function (e) {

    $(window).keyup(function ( e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if(code == 9){
            console.log("Tab");
            var tag = document.getElementById("tagline").value;
            console.log(tag);

            var div = document.createElement('div');
            div.setAttribute('class','control');
            div.setAttribute('id','1');
            div.setAttribute('onclick','removeTag()');

            div.innerHTML=`
            <div class="tags has-addons">
                <a class="tag is-link">${tag}</a>
                <a class="tag is-delete"></a>
            </div>
            `;

            console.log(div);
document.getElementById('addtag').appendChild(div);
        }
        
    });
    
});

function removeTag(){

    var elem = document.getElementById('1');
    console.log(elem);
    elem.parentNode.removeChild(elem);

}
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


/*
pull

 */





