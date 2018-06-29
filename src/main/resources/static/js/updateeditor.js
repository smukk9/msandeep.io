function removeTag(e) {


    //get the id of the clicked evernt
    // var clickedElement = e.target.id;
    var elem = document.getElementById(e);
    $(elem).parent().remove()

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
            var cleanTitle = data.title;
            console.log(cleanTitle);

            var tele = document.getElementById("article-title");
            $(tele).val(cleanTitle);

            // set content
            var decoded = decodeURIComponent(data.content);
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