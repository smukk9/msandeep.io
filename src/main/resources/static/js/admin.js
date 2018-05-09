
$('.close-modal').on('click', function (e) {

    console.log('Closing the modal');
    var modalid = document.getElementById('modal-tag-create');
    modalid.classList.remove('is-active');
});

$('#create-tag').on('click', function (e) {

    console.log('Launching the modal');
    var modalid = document.getElementById('modal-tag-create');
    modalid.classList.add('is-active');
});

var tags ={
    tagnames:[]
};


//TO-DO remove the old tag value before saving the new one .
function createTag() {

    $( ".tag-name" ).each(function( index ) {
        console.log( index + ": " + $( this ).text() );

        tags.tagnames.push({
            name: $(this).text()
        });

    });

    console.log("print")
    console.log(JSON.stringify(tags));
    
}

createTag();



$(".save-tag").on('click', function (e) {
    console.log("saving the following tag");
    createTag();
    console.log(tags);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v1/tag",
        data: JSON.stringify(tags), // convert array to JSON
        dataType: 'json',
        cache: false,
        timeout: 100000,
        success: function (data) {

            console.log("SUCCESS : ", data);

            $(".confirm-notify").text("Tags Saved");;
            $(".confirm-notify").fadeOut(3000);
           $("#addtag").empty();

        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });



});

//Add new tag to the tag display panel
$('#tagline').on('click', function (e) {
    var tag = document.getElementById("tagName").value;
    var div = document.createElement('div');
    div.setAttribute('class','control');
    div.setAttribute('class',tag);
    div.setAttribute('id',tag);
    div.setAttribute('onclick','removeTag(this.id)');
    div.innerHTML=`
        <div class="tags has-addons">
                <a class="tag is-link tag-value tag-name">${tag}</a>
                <a class="tag is-delete"></a>
            </div>
            `;

            console.log(div);
            document.getElementById('addtag').appendChild(div);

            //set the value of tagName input to empty
            $('#tagName').val("");


});

//remove the tag from the tag display panel
function removeTag(e) {

    console.log("Remove tag initated")
    console.log(e);
    //get the id of the clicked evernt
   // var clickedElement = e.target.id;
    console.log("clicked Element"+ e)
    var elem = document.getElementById(e);
    console.log(elem);
    elem.parentNode.removeChild(elem);
}
