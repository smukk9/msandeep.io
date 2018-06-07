$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/api/v1/article/11",
        cache: true,
        timeout: 100000,
        success: function (data) {


            // console.log(data[0].tagName );
            // var div = document.createElement('div');
            // div.setAttribute('class','control');
            // $(div).addClass(data[0].tagName );
            // div.innerHTML=`
            // <div class="tags has-addons">
            //     <a class="tag is-link ">${data[0].tagName }</a>
            //     <a class="tag is-delete saveTag" id=${data[0].id} onclick="removeTag(this.id)"></a>
            // </div>
            // `;
            // document.getElementById('addtag').appendChild(div);

            //set title
            var cleanTitle = data.title.substring(1, data.title.length-1);
            console.log(cleanTitle);
            var tele = document.getElementById("article-title");
            $(tele).text(cleanTitle);

           // set content
            var acontent = document.getElementById("article-content");
            $(acontent).html(data.content.substring(1, data.content.length-1));

            console.log(acontent);


        },
        error: function (e) {

x1

        }
    });
});