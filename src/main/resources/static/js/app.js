(function(){
    const KEY = 'theme'; // 'light' | 'dark'
    const body = document.body;

    // --- Aplica desde localStorage o preferencia del SO ---
    const saved = localStorage.getItem(KEY);
    const prefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    if (saved === 'dark' || (!saved && prefersDark)) {
        body.classList.add('dark');
    }

    // --- Cambia icono según estado ---
    function setIcon(btn){
        if(!btn) return;
        const dark = body.classList.contains('dark');
        btn.textContent = dark ? '☀️' : '🌙';
        btn.title = dark ? 'Cambiar a tema claro' : 'Cambiar a tema oscuro';
    }

    // --- Alternar tema ---
    function toggle(){
        body.classList.toggle('dark');
        const dark = body.classList.contains('dark');
        localStorage.setItem(KEY, dark ? 'dark' : 'light');
        setIcon(document.getElementById('themeToggle'));
        setIcon(document.getElementById('themeToggleFab'));
    }

    // --- Botón en layout (navbar) ---
    const btn = document.getElementById('themeToggle');
    if (btn) {
        setIcon(btn);
        btn.addEventListener('click', toggle);
    }

    // --- Botón flotante en auth pages ---
    const fab = document.getElementById('themeToggleFab');
    if (fab){
        setIcon(fab);
        fab.addEventListener('click', toggle);
    }

})();
