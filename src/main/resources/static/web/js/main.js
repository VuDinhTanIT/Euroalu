 $(document).ready(function() {
        $('#menu-toggle').on('click', function() {
            $('#mobile-menu').toggle();
            // Cập nhật trạng thái aria-expanded
            const isExpanded = $(this).attr('aria-expanded') === 'true';
            $(this).attr('aria-expanded', !isExpanded);
        });
    });