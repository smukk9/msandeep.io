$( document ).ready(function() {

    console.log("Loading summernot");
   var notif= document.getElementsByClassName("notification");
    $(notif).hide();
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



//Delete a tag from the tag display planel
function removeTag(e) {


    //get the id of the clicked evernt
    // var clickedElement = e.target.id;
    var elem = document.getElementById(e);
    $(elem).parent().remove()

}



//toggel function to show the burger in mobile view

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


$("#tagline").on('keyup', function (e) {

    var $field = $(this);


    // this is the value before the keypress
    var beforeVal = $field.val();

    var keyCode = e.keyCode;


    if(keyCode==8 & beforeVal.length >0){

        cleanSearch();

        var notif= document.getElementsByClassName("notification");
        $(notif).hide();

    }
    if(beforeVal && beforeVal.length >1){

      cleanSearch()

        $.ajax({
            type: "GET",
            url: "/api/v1/tag/search?tagName="+beforeVal,
            cache: true,
            timeout: 100000,
            success: function (data) {

                for (var i in data){
                        var anchor = document.createElement("a");
                        anchor.setAttribute('class', 'panel-block');
                        anchor.setAttribute('id', data[i].tagName);
                        anchor.setAttribute('onclick','addToInputLine(this.id)');
                        anchor.setAttribute('value',data[i].id);
                       $(anchor).text(data[i].tagName);
                   document.getElementById('searchVal').appendChild(anchor);
                }

            },
            error: function (e) {

                var notif = document.getElementsByClassName("notification");
                $(notif).show();

            }
        });
    }
});

function cleanSearch(){
    
    var panel= document.getElementsByClassName("panel-block");
    $(panel).remove();
}

function addToInputLine(event) {



    cleanSearch();
    console.log("checking the event "+ event);
    var tagValue = event;
    //set the tagvalue to the inputLine
    var inputDom = document.getElementById("tagline");
    addTagGroup(event);
    $(inputDom).val("");
    
}


//Gets the tagName and does an ajax call to tag/search?tagName= api to get the id. Should optimize later

function addTagGroup(tagValue) {


    $.ajax({
        type: "GET",
        url: "/api/v1/tag/search?tagName="+tagValue,
        cache: true,
        timeout: 100000,
        success: function (data) {

            console.log(data[0].tagName );
            var div = document.createElement('div');
            div.setAttribute('class','control');
            $(div).addClass(data[0].tagName );
            div.innerHTML=`
            <div class="tags has-addons">
                <a class="tag is-link ">${data[0].tagName }</a>
                <a class="tag is-delete saveTag" id=${data[0].id} onclick="removeTag(this.id)"></a>
            </div>
            `;
            document.getElementById('addtag').appendChild(div);



        },
        error: function (e) {

            var notif = document.getElementsByClassName("notification");
            $(notif).show();

        }
    });


    
}



/*
From here all the function and code is related to following functionality

    1-Save Article
    2-clear article page
    3-Temporary stoarge function
        Redo and Undo
    4-Cancel .

 */

function saveArticle() {

    //Get the Id of the tags
    var tagIds = $.map($(".saveTag"), function(n, i){
        return n.id;
    });

    var title = document.getElementById('article-title').value;
    var content = $('#summernote').summernote('code');


        // article object
        var article = {
            "title": title,
            "article": content,
            "tagId":tagIds
        }

        console.log(article);



    
}

