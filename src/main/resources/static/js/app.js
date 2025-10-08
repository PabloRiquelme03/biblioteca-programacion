(function () {
    const KEY = "theme"; // 'light' | 'dark'
    const body = document.body;

    // --- Aplica desde localStorage o preferencia del SO ---
    const saved = localStorage.getItem(KEY);
    const prefersDark =
        window.matchMedia &&
        window.matchMedia("(prefers-color-scheme: dark)").matches;
    if (saved === "dark" || (!saved && prefersDark)) {
        body.classList.add("dark");
    }

    // --- Cambia icono según estado ---
    function setIcon(btn) {
        if (!btn) return;
        const dark = body.classList.contains("dark");
        btn.textContent = dark ? "☀️" : "🌙";
        btn.title = dark
            ? "Cambiar a tema claro"
            : "Cambiar a tema oscuro";
    }

    // --- Alternar tema ---
    function toggle() {
        body.classList.toggle("dark");
        const dark = body.classList.contains("dark");
        localStorage.setItem(KEY, dark ? "dark" : "light");
        setIcon(document.getElementById("themeToggle"));
        setIcon(document.getElementById("themeToggleFab"));
    }

    // --- Botón en layout (navbar) ---
    const btn = document.getElementById("themeToggle");
    if (btn) {
        setIcon(btn);
        btn.addEventListener("click", toggle);
    }

    // --- Botón flotante en auth pages ---
    const fab = document.getElementById("themeToggleFab");
    if (fab) {
        setIcon(fab);
        fab.addEventListener("click", toggle);
    }

    // --- Inicializa y autodesvanece TOASTS ---
    document.addEventListener("DOMContentLoaded", () => {
        const toasts = document.querySelectorAll(".toast.auto-dismiss");
        toasts.forEach((t) => {
            const toast = new bootstrap.Toast(t, { delay: 3500 });
            toast.show();
            setTimeout(() => toast.hide(), 4000);
        });
    });

    // --- Fallback: si hay alertas normales (sin toast) ---
    document.addEventListener("DOMContentLoaded", () => {
        const alerts = document.querySelectorAll(".alert.auto-dismiss");
        alerts.forEach((el) => {
            setTimeout(() => {
                try {
                    const inst = bootstrap.Alert.getOrCreateInstance(el);
                    inst.close();
                } catch (_) {
                    el.style.transition = "opacity .5s ease";
                    el.style.opacity = "0";
                    setTimeout(() => el.remove(), 500);
                }
            }, 3500);
        });
    });
})();
