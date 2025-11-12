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
// =========================
// Animación fade-in al hacer scroll
// =========================
document.addEventListener("DOMContentLoaded", () => {
    const faders = document.querySelectorAll(".fade-in-section");

    const appearOptions = {
        threshold: 0.2,
        rootMargin: "0px 0px -50px 0px"
    };

    const appearOnScroll = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (!entry.isIntersecting) return;
            entry.target.classList.add("is-visible");
            observer.unobserve(entry.target);
        });
    }, appearOptions);

    faders.forEach(fader => appearOnScroll.observe(fader));
});
// =========================
// Animaciones fade-in al hacer scroll (+ stagger automático)
// =========================
document.addEventListener("DOMContentLoaded", () => {
    // Elementos con fade-in individual
    const singles = document.querySelectorAll(
        ".fade-in-section, .fade-in-up, .fade-in-right, .fade-in-scale"
    );

    const ioOptions = { threshold: 0.2, rootMargin: "0px 0px -60px 0px" };
    const io = new IntersectionObserver((entries, obs) => {
        entries.forEach(entry => {
            if (!entry.isIntersecting) return;
            entry.target.classList.add("is-visible");
            obs.unobserve(entry.target);
        });
    }, ioOptions);

    singles.forEach(el => io.observe(el));

    // Contenedores con stagger (hijos entran escalonados)
    const staggers = document.querySelectorAll(".fade-stagger");
    const ioStagger = new IntersectionObserver((entries, obs) => {
        entries.forEach(entry => {
            if (!entry.isIntersecting) return;

            const children = Array.from(entry.target.children);
            children.forEach((child, i) => {
                // si el hijo ya lleva clase fade-in, respetamos; si no, aplicamos genérica
                if (
                    !child.classList.contains("fade-in-section") &&
                    !child.classList.contains("fade-in-up") &&
                    !child.classList.contains("fade-in-right") &&
                    !child.classList.contains("fade-in-scale")
                ) {
                    child.classList.add("fade-in-up");
                }
                // delay incremental (100–140ms por elemento)
                const step = child.dataset.step ? parseInt(child.dataset.step, 10) : 110;
                child.style.transitionDelay = `${i * step}ms`;
                // observar cada hijo para añadir .is-visible cuando corresponda
                io.observe(child);
            });

            obs.unobserve(entry.target);
        });
    }, { threshold: 0.15, rootMargin: "0px 0px -60px 0px" });

    staggers.forEach(el => ioStagger.observe(el));
});
// === IntersectionObserver para revelar elementos al hacer scroll ===
(() => {
    const io = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // Agrega la clase 'is-visible' al intersectar
                entry.target.classList.add('is-visible');

                // Si el target es el CTA (tiene .cta-centered), añade 'visible' también
                if (entry.target.classList.contains('cta-centered')) {
                    entry.target.classList.add('visible');
                }

                // Una vez visible, dejamos de observarlo
                io.unobserve(entry.target);
            }
        });
    }, { root: null, rootMargin: "0px 0px -10% 0px", threshold: 0.1 });

    // Seleccionamos todos los elementos que deben animarse
    const revealTargets = document.querySelectorAll(
        '.fade-in-up, .fade-in-section, .fade-stagger, .cta-centered'
    );
    revealTargets.forEach(el => io.observe(el));
})();
