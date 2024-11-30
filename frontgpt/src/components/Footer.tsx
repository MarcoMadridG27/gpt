
const Header = () => {
    return (
        <header style={styles.header}>
            <h1 style={styles.title}>FrontGPT</h1>
            <nav style={styles.nav}>
                <a href="#home" style={styles.link}>Inicio</a>
                <a href="#about" style={styles.link}>Acerca</a>
                <a href="#contact" style={styles.link}>Contacto</a>
            </nav>
        </header>
    );
};

const styles = {
    header: {
        backgroundColor: "#333",
        color: "#fff",
        padding: "10px 20px",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
    },
    title: {
        margin: 0,
        fontSize: "1.5rem",
    },
    nav: {
        display: "flex",
        gap: "15px",
    },
    link: {
        color: "#fff",
        textDecoration: "none",
    },
};

export default Header;
