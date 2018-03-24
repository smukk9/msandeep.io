$(function() {
    $('section01').on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({ scrollTop: $($(this).attr('href')).offset().top}, 500, 'linear');
    });
});
$(document).ready(function() {
    $.ajax({
        url: "/api/v1/article/1"
    }).then(function(data) {
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        console.log(data)
    });
});