$(document).ready(function(){


    var urlPath = document.URL.split("/");
    var tagId = urlPath[urlPath.length-2];

    $.ajax({
        type: "GET",
        url: "/api/v1/tag/"+tagId+"/article",
        //tag/"+tagId+"/article",
        cache: true,
        timeout: 100000,
        success: function (data) {

            console.log(data);



            data.forEach(function (element){
                console.log(element);

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

console.log("no api defined.")

        }
    });
});