import i18n from "i18next";
import { initReactI18next } from "react-i18next";

// Import translation files
import translationEN from "./locales/en.json"; // English
import translationKA from "./locales/ka.json"; // Georgian

const resources = {
    ka: { translation: translationKA },
    en: { translation: translationEN },
};

i18n.use(initReactI18next).init({
    resources,
    lng: localStorage.getItem("language") || "en",
    interpolation: { escapeValue: false },
});

export default i18n;
