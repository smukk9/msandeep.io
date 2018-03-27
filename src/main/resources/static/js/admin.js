
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
    console.log("saving the tag");
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

        },
        error: function (e) {

            console.log("ERROR : ", e);

        }
    });

});
