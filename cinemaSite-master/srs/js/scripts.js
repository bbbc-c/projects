$(document).ready( function() {
    // Изменение даты на сегодня и блокирование всех дат которые были до
    let date = new Date().toISOString().substring(0, 10);
    // блокировка дат, которые стоят раньше сегодня 
    $('#date').attr('min',date);
    $('#date').val(date);

    // Добавление текста в info-title. На какой день отображаются фильмы 
    $('#date').change(function(){
        let date = new Date().toISOString().substring(0, 10);
        let dateForm = $('#date').val();
        if(date !== dateForm)
            $('#info-title').text("Фильмы на " + dateForm + ":");
        else         
            $('#info-title').text("Фильмы на сегодня:");
    });
    // открыть popup
    $('#popup-open').click(function(){    
        openPopup("popup-overlay");
    });
    // закрывает popup
    $('#popup-close').click(function(){
        closePopup("popup-overlay");
    });
    // закрывает popup c сообщением "оповещение"
    $('#alert-ok').click(function(){
        closePopup("popup-overlay-alert");
    });
    // открывает popup с регистрацией при нажатие на кнопку регистрации в меню
    $('#registration').click(function(){
        openPopup("popup-overlay-registration");
    });
    // закрывает popup с регистрацией при нажатие на кнопку регистрации в меню
    $('#popup-close-registration').click(function(){
        closePopup("popup-overlay-registration");
    });
    // открывает popup с авторизацией при нажатие на кнопу войти в меню
    $('#login').click(function(){
        openPopup("popup-overlay-login");
    });
    // закрывает popup с авторизацией при нажатие на кнопу войти в меню
    $('#popup-close-login').click(function(){
        closePopup("popup-overlay-login");
    });
    // закрывает pupup с авторизацией и открывает с popup регистрацией  
    // при нажатие на кнопку регистрация в popup авторизации 
    $('#redirect-registration').click(function(){
        closePopup("popup-overlay-login");
        openPopup("popup-overlay-registration");
    });
    // проставляет маску в input[type = tel]
    $("#phone").mask("+7(999) 999-9999");


});

// Очищение полей формы с фильтром.
$(function(){
    $('#reset').click(function(){
        $('#filtr').each(function(){
            this.reset();
        });
        $('#date').val(new Date().toISOString().substring(0, 10));   
        $('#info-title').text("Фильмы на сегодня:"); 
    });

});
// открывает отображение в зависимости от выбраного пункта в левом меню 
// item - отображемый блок
function openItem(item){
    $('.active').css('display','none');
    $('.active').removeClass('active');
    $('#'+item).css('display','flex');
    $('#'+item).addClass('active');
}
// открывает форму для изминения в настрйоках
// item - какую найстройку открыть
function openForm(item){  
    $('#form-change-' + item).css('display','flex');     
    // удаляем кнопку изменить
    $('#button-open-' + item).remove();
    // дабовляем кнопку отменить
    $('#setting-line-' + item).append("<a id=button-close-"+item+" onclick=closeForm('"+item+"')>Отменить</a>");
}
// закрывает форму для изменение в настройках
// item - какую найстройкую нужно закрыть
function closeForm(item){    
    $('#form-change-'+ item).css('display','none');   
    // удаляем кнопку отменить 
    $('#button-close-'+item).remove();
    // дабовляем кнопку измнить
    $('#setting-line-' + item).append("<a id=button-open-"+item+" onclick=openForm('"+item+"')>Изменить</a>");
}
// открывать popup
// в передаваемом значение должен быть указа 
// id на popup который нужно открыть
function openPopup(idPopup) {
    $('.overlay').css('display','none');
    $('#' + idPopup).fadeIn();
    // отключить возможно нажатия на задний фон
    $('#'+ idPopup).addClass('disabled');
}
// закрывает popup
// в передаваемом значение должен быть указа 
// id на popup который нужно открыть
function closePopup(idPopup){
    $('#' + idPopup).fadeOut();     
}
// алерт
function openAlert(message, state = "default"){
    // Вызывает сообщение для оповещение клиента
    // default - Обычное сообщение
    // success - Удачное сообщение
    // unsuccess - Неудачное сообщение
    openPopup("popup-overlay-alert");
    $('#message').text(message);
    switch(state){
        case "unsuccess":               
            $('#message').prepend('<i class="bi bi-patch-exclamation"></i>');         
            $('#message').css('color','red'); 
            break;
        case "success":
            $('#message').prepend('<i class="bi bi-patch-check"></i>');         
            $('#message').css('color','green'); 
            break;
        default:
            break;
    }
}
