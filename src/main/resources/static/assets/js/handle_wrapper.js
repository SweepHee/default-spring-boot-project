class HandleWrapper {
    #target
    #compile
    #template
    #data
    constructor() {}

    getData() {
        return this.#data;
    }
    setData(data) {
        this.#data = data || {items: []};
    }

    getTemplate() {
        return this.#template;
    }
    setTemplate(template) {
        this.#template = template;
    }

    getTarget() {
        return this.#target;
    }
    setTarget(target) {
        this.#target = target;
    }

    getCompile() {
        return this.#compile;
    }
    setCompile(compile) {
        this.#compile = compile;
    }

    compile(obj) {
        this.#target = obj;
        if (this.#template && this.#data) {
            this.#compile = Handlebars.compile(this.#template);
            this.#target.html(this.#compile(this.#data));
        }
    }

    fetch(data) {
        this.#data.items = this.#data.items.concat(data);
        if (this.#template && this.#data) {
            if (!this.#compile) {
                this.#compile = Handlebars.compile(this.#template);
            }
            this.#target.html(this.#compile(this.#data));
        }
    }

}
