$(document).ready(function(){

    var urlPath = document.URL.split("/");
    var articleId = urlPath[urlPath.length-1];

    $.ajax({
        type: "GET",
        url: "/api/v1/article/"+articleId,
        cache: true,
        timeout: 100000,
        success: function (data) {

            //set title
            var cleanTitle = data.title.substring(1, data.title.length-1);
            $(crubtitle).text(cleanTitle);

            var tele = document.getElementById("article-title");
            $(tele).text(cleanTitle);

           // set content
            var decoded = decodeURIComponent(data.content);
          var acontent=document.getElementById('article-content')
            $(acontent).html(decoded.substring(1, decoded.length-1))

            //set tags
            data.tags.forEach(function(element) {
                var tag_anchor = document.createElement('a')
                tag_anchor.setAttribute('class','tag is-rounded is-size-6 is-success');
                tag_anchor.setAttribute('id',element.tagName);
                var tag_anchor_text = document.createTextNode(element.tagName);
             //   tag_anchor.setContent('data.tagName');
                tag_anchor.appendChild(tag_anchor_text);
                document.getElementById('tag-list').appendChild(tag_anchor);

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