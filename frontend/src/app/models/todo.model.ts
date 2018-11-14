class ToDo {
    id:string;
    title: string;
    text: string;
    done: boolean;
    dateTime: Date;
    status: string;

    constructor(
    ){
        this.title = ""
        this.text = ""
        this.done = false
        this.dateTime = new Date()
        this.status = ""
    }
}

export default ToDo;
