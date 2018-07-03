
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
        $(notif).addClass("is-hidden");

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

                    //Get the Id of the tags
                    var tagIds = $.map($(".saveTag"), function(n, i){
                        return n.id;
                    });


                    if($.inArray(data[i].id.toString(),tagIds) < 0){

                        var anchor = document.createElement("a");
                        anchor.setAttribute('class', 'panel-block');
                        anchor.setAttribute('id', data[i].tagName);
                        anchor.setAttribute('onclick','addToInputLine(this.id)');
                        anchor.setAttribute('value',data[i].id);
                        $(anchor).text(data[i].tagName);
                        document.getElementById('searchVal').appendChild(anchor);
                    }else{


                    }


                }


            },
            error: function (e) {


                var notif= document.getElementsByClassName("notification");
                $(notif).removeClass("is-hidden");

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



$(document).ready(function(){

    var urlPath = document.URL.split("/");
    var articleId = urlPath[urlPath.length-1];

    console.log(articleId);
    $.ajax({
        type: "GET",
        url: "/api/v1/article/"+articleId,
        cache: true,
        timeout: 100000,
        success: function (data) {

            //set title
            var cleanTitle = data.title.substring(1, data.title.length-1);
            ;
            console.log(cleanTitle);

            var tele = document.getElementById("article-title");
            $(tele).val(cleanTitle);

            // set content
            var clean_content= data.content.replace(/^"%3Cp%3E+/,'');

            console.log(clean_content);
            var decoded = decodeURIComponent(clean_content.substring(0,clean_content.length-1));
            var acontent=document.getElementById('article-content')
            $(myTextarea).html(decoded)

            //set tags
            data.tags.forEach(function(element) {
               //  var tag_anchor = document.createElement('a')
               //  tag_anchor.setAttribute('class','tag is-rounded is-size-6 is-success');
               //  tag_anchor.setAttribute('id',element.tagName);
               //  var tag_anchor_text = document.createTextNode(element.tagName);
               //  console.log(tag_anchor)
               //
               //
               //  document.getElementById('addtag').appendChild(tag_anchor);
               // var tagd= document.getElementById(element.tagName)
               //  $(tagd).text(element.tagName);

                var div = document.createElement('div');
                div.setAttribute('class','control');
                $(div).addClass(element.tagName );
                div.innerHTML=`
            <div class="tags has-addons">
                <a class="tag is-link ">${element.tagName  }</a>
                <a class="tag is-delete saveTag" id=${element.tagName } onclick="removeTag(this.id)"></a>
            </div>
            `;
                document.getElementById('addtag').appendChild(div);



            });

            //set year in breadcrumb

            var date = new Date(data.createDate);
            var year= date.getFullYear();




        },
        error: function (e) {

            var acontent=document.getElementById('article-content')
            $(acontent).text("No article was found")

        }
    });
});



//Update the article function

$('.close-modal').on('click', function (e) {


    var modalid = document.getElementById('modal-tag-create');
    modalid.classList.remove('is-active');
});


function saveArticle() {

    //Get the Id of the tags
    var tagIds = $.map($(".saveTag"), function(n, i){
        return n.id;
    });

    var title = document.getElementById('article-title').value;
    var content =  tinyMCE.activeEditor.getContent();

    // article object
    var article =[];
    //  var tagsList=[];
    var urlPath = document.URL.split("/");
    var articleId = urlPath[urlPath.length-1];
    article.push({
        "id":articleId,
        "title": title,
        "content":encodeURIComponent(content),
        "tags": tagIds

    });

    console.log(article);
    $.ajax({
        url: "/api/v1/article",
        type: "put",
        data: JSON.stringify(article),
        datatype:"json",
        contentType: "application/json; charset=utf-8",
        success: function(d) {


            var modalid = document.getElementById("modal-tag-create");
            modalid.classList.add('is-active');
            tinyMCE.get('myTextarea').setContent('');
            $('#article-title').val("");
            $("#addtag").empty();

        },error: function (e) {

            var notif = document.getElementsByClassName("notification");
            $(notif).show();

        }
    });


}