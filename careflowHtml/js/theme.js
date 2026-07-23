(function() {
    const lightTheme = {
        '--sidebar': '#0F172A',
        '--background': '#F1F5F9',
        '--card': '#FFFFFF',
        '--text': '#334155',
        '--shadow': '0 10px 25px rgba(0,0,0,.08)',
        '--table-header': '#2563EB',
        '--table-row-even': '#f8fafc',
        '--table-row-hover': '#dbeafe',
        '--input-bg': '#fff',
        '--input-border': '#ddd',
        '--button-bg': '#2563eb'
    };

    const darkTheme = {
        '--sidebar': '#111827',
        '--background': '#0f172a',
        '--card': '#111827',
        '--text': '#e2e8f0',
        '--shadow': '0 10px 25px rgba(0,0,0,.4)',
        '--table-header': '#1e293b',
        '--table-row-even': '#111827',
        '--table-row-hover': '#334155',
        '--input-bg': '#1f2937',
        '--input-border': '#334155',
        '--button-bg': '#2563eb'
    };

    function applyTheme(theme) {
        const button = document.getElementById('themeBtn');
        const isDark = theme === 'dark';
        document.body.classList.toggle('dark', isDark);
        const vars = isDark ? darkTheme : lightTheme;
        Object.entries(vars).forEach(([name, value]) => document.documentElement.style.setProperty(name, value));
        if (button) {
            button.textContent = isDark ? 'Light Mode' : 'Dark Mode';
        }
    }

    function toggleTheme() {
        const nextTheme = document.body.classList.contains('dark') ? 'light' : 'dark';
        applyTheme(nextTheme);
        localStorage.setItem('careflowTheme', nextTheme);
    }

    function initTheme() {
        const savedTheme = localStorage.getItem('careflowTheme') || 'light';
        applyTheme(savedTheme);
        const button = document.getElementById('themeBtn');
        if (!button) return;
        button.type = 'button';
        button.addEventListener('click', toggleTheme);
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initTheme);
    } else {
        initTheme();
    }

    window.careflowThemeLoaded = true;
})();
