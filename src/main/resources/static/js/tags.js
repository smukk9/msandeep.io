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