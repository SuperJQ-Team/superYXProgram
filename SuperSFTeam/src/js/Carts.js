import {Cookies} from "./Cookies";
import index from "@/components/Index.vue";

export class Carts {
    static add(obj) {
        let list = Cookies.get("carts");
        if (list == null) list = [];

        for (let i in list) {
            if (list[i].id === obj.id) {
                // return "AlreadyHave";
                list[i].number += obj.number;
                Cookies.set("carts", list, 15);
                return true;
            }
        }

        list.push(obj);
        Cookies.set("carts", list, 15);
        return true;
    }

    static get() {
        return Cookies.get("carts") ?? [];
    }

    static setnum(obj, number) {
        let list = Cookies.get("carts");
        if (list == null) return false;
        for (let i in list) {
            if (list[i].id === obj.id) {
                list[i].number = number;
            }
        }
        Cookies.set("carts", list, 15);
        return true;
    }

    static remove(id) {
        let list = Cookies.get("carts");
        if (list == null) return;
        let obj = null;
        for (let i in list) {
            if (list[i].id === id) {
                obj = i;
            }
        }
        if (obj === null) return;
        list.splice(obj, 1);
        Cookies.set("carts", list, 15);
    }

    static clear() {
        Cookies.remove("carts");
    }
}