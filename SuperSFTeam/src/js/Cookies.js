export class Cookies {
    static set(cname, cvalue, exdays = 1) {
        let d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        let expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + JSON.stringify(cvalue) + "; " + expires;
    }

    static get(cname) {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(name) === 0)
                return JSON.parse(c.substring(name.length, c.length));
        }
        return null;
    }

    static check(cname) {
        return Cookies.get(cname) != null;
    }

    static remove(cname) {
        document.cookie = cname + "=null; expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}