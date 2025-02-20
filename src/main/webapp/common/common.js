/*
    原生alert
 */
 (function($) {
    $.alerts = {
         time:null,
        // These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
        verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
        horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
        repositionOnResize: true,           // re-centers the dialog on window resize
        overlayOpacity: .3,                // transparency level of overlay
        overlayColor: '#cccccc',               // base color of overlay
        draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
        okButton: '&nbsp;OK&nbsp;',         // text for the OK button
        cancelButton: '&nbsp;Cancel&nbsp;', // text for the Cancel button
        dialogClass: null,                  // if specified, this class will be applied to all dialogs
        
        // Public methods
        
        alert: function(message, title, callback) {
            if( title == null ) title = 'Alert';
            $.alerts._show(title, message, null, 'alert', function(result) {
                if( callback ) callback(result);
            });
        },
        
        confirm: function(message, title, callback) {
            if( title == null ) title = 'Confirm';
            $.alerts._show(title, message, null, 'confirm', function(result) {
                if( callback ) callback(result);
            });
        },
            
        prompt: function(message, value, title, callback) {
            if( title == null ) title = 'Prompt';
            $.alerts._show(title, message, value, 'prompt', function(result) {
                if( callback ) callback(result);
            });
        },
        
        // Private methods
        _show: function(title, msg, value, type, callback) {
            $.alerts._hide();
            $.alerts._overlay('show');
            var iframeContainer ="";
            $("BODY").append(
              '<div id="popup_container"> ' +iframeContainer+
                '<h1 id="popup_title"></h1>' +                
                '<div id="popup_content">' +
                  '<div id="popup_message"></div>' +
                '</div>' +
              '</div>');
            
            if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
            
            $("#popup_container").css({
                position: 'fixed',
                zIndex: 99999,
                padding: 0,
                margin: 0
            });
            $("#popup_title").text(title);
            $("#popup_content").addClass(type);
            $("#popup_message").text(msg);
            $("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
            $("#popup_container").css({
                minWidth: $("#popup_container").outerWidth(),
                maxWidth: $("#popup_container").outerWidth()
            });
            
            
            $.alerts._reposition();
            $.alerts._maintainPosition(true);
            
            switch( type ) {
                case 'alert':
                    $("#popup_message").after('<div id="popup_panel" style="text-align: right;"><button  value="' + $.alerts.okButton + '" id="popup_ok">' + $.alerts.okButton + '</botton></div>');
                
                    $("#popup_ok").button({ label:"confirm"}).click( function() {
                        if($.alerts.time){
                           clearTimeout($.alerts.time);
                           $.alerts.time = null;
                        }
                        $.alerts._hide();
                        callback(true);
                    });
                    $("#popup_ok").focus().keypress( function(e) {
                        if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
                    });
                break;
                case 'confirm':
                    $("#popup_message").after('<div id="popup_panel" style="text-align: right;"><button  value="' + $.alerts.okButton + '" id="popup_ok">' + $.alerts.okButton + '</botton> <button value="' + $.alerts.cancelButton + '" id="popup_cancel">' + $.alerts.cancelButton + '</botton></div>');
                    $("#popup_ok").button({ label:"confirm"}).click( function() {
                        $.alerts._hide();
                        if( callback ) callback(true);
                    });
                    $("#popup_cancel").button({ label:"cancel"}).click( function() {
                        $.alerts._hide();
                        if( callback ) callback(false);
                    });
                    $("#popup_ok").focus();
                    $("#popup_ok, #popup_cancel").keypress( function(e) {
                        if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
                        if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
                    });
                break;
                case 'prompt':
                    $("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><button  value="' + $.alerts.okButton + '" id="popup_ok">' + $.alerts.okButton + '</botton> <button  value="' + $.alerts.cancelButton + '" id="popup_cancel">' + $.alerts.cancelButton + '</botton></div>');
                    $("#popup_ok").button({ label:"confirm"}).click( function() {
                        var val = $("#popup_prompt").val();
                        $.alerts._hide();
                        if( callback ) callback( val );
                    });
                    $("#popup_cancel").button({ label:"cancel"}).click( function() {
                        $.alerts._hide();
                        if( callback ) callback( null );
                    });
                    $("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
                        if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
                        if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
                    });
                    if( value ) $("#popup_prompt").val(value);
                    $("#popup_prompt").focus().select();
                break;
            }
            
            // Make draggable
            if( $.alerts.draggable ) {
                try {
                    $("#popup_container").draggable({ handle: $("#popup_title") });
                    $("#popup_title").css({ cursor: 'move' });
                } catch(e) { /* requires jQuery UI draggables */ }
            }
        },
        
        _hide: function() {
            $("#popup_container").remove();
            $.alerts._overlay('hide');
            $.alerts._maintainPosition(false);
        },
        
        _overlay: function(status) {
            switch( status ) {
                case 'show':
                    $.alerts._overlay('hide');
                    var iframeoverlay ="";
                    $("BODY").append('<div id="popup_overlay">'+iframeoverlay+'</div>');
                    $("#popup_overlay").css({
                        position: 'absolute',
                        zoom: 1,
                        zIndex: 99998,
                        top: '0px',
                        left: '0px',
                        width: '100%',
                        height: $(document).height(),
                        background: $.alerts.overlayColor,
                        opacity: $.alerts.overlayOpacity
                    });
                        
                break;
                case 'hide':
                    $("#popup_overlay").remove();
                break;
            }
        },
        
        _reposition: function() {
            
            var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
            var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
            if( top < 0 ) top = 0;
            if( left < 0 ) left = 0;

            $("#popup_container").css({
                top: top + 'px',
                left: left + 'px'
            });
            $("#popup_overlay").height( $(document).height() );
            
        },
        
        _maintainPosition: function(status) {
            if( $.alerts.repositionOnResize ) {
                switch(status) {
                    case true:
                        $(window).bind('resize', $.alerts._reposition);
                    break;
                    case false:
                        $(window).unbind('resize', $.alerts._reposition);
                    break;
                }
            }
        }
        
    }
    
    // Shortuct functions
    jAlert = function(message, title, callback) {
        $.alerts.alert(message, title, callback);
    }
    
    jConfirm = function(message, title, callback) {
        $.alerts.confirm(message, title, callback);
    };
        
    jPrompt = function(message, value, title, callback) {
        $.alerts.prompt(message, value, title, callback);
    };
    jHide = function(timeout){
        if(timeout){
        
           $.alerts.time = setTimeout($.alerts._hide,timeout);
        }
    };
})(jQuery);
