(function () {
    // ==== Auto-cierre de alertas Bootstrap (con clase .auto-dismiss) ====
    document.addEventListener('DOMContentLoaded', () => {
        const alerts = document.querySelectorAll('.alert.auto-dismiss');
        alerts.forEach(el => {
            setTimeout(() => {
                try {
                    const inst = bootstrap.Alert.getOrCreateInstance(el);
                    inst.close();
                } catch (_) {
                    el.style.transition = 'opacity .5s ease';
                    el.style.opacity = '0';
                    setTimeout(() => el.remove(), 500);
                }
            }, 3500);
        });
    });

    // ==== Contador para notas (0/250) ====
    document.addEventListener('DOMContentLoaded', () => {
        const input = document.getElementById('notaContenido');
        const cnt   = document.getElementById('notaCnt');
        if (!input || !cnt) return;

        const update = () => {
            let v = input.value || '';
            if (v.length > 250) {
                v = v.slice(0, 250);
                input.value = v;
            }
            cnt.textContent = v.length.toString();
        };
        update();
        input.addEventListener('input', update);
    });

})();
