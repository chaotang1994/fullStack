export class Product {
    id:number;
    name:string;
    category:string;
    quantity:number;
    condition:string;
    price:number;
    imgURL:any;

    constructor(
        id:number,
        name:string,
        category:string,
        quantity:number,
        condition:string,
        price:number,
        imgURL:any,

    ){
        this.id=id;
        this.name=name;
        this.category=category;
        this.quantity=quantity;
        this.condition=condition;
        this.price=price;
        this.imgURL=imgURL;
    }
    public get _id(): number {
        return this.id;
    }
    public set _id(id: number) {
        this.id = id;
    }

    public get _name(): string {
        return this.name;
    }
    public set _name(name: string) {
        this.name = name;
    }

    public get _category(): string {
        return this.category;
    }
    public set _category(category: string) {
        this.category = category;
    }

    public get _quantity(): number {
        return this.quantity;
    }
    public set _quantity(quantity: number) {
        this.quantity = quantity;
    }

    public get _price(): number {
        return this.price;
    }
    public set _price(price: number) {
        this.price = price;
    }

    public get _condition(): string {
        return this.category;
    }
    public set _condition(condition: string) {
        this.condition = condition;
    }

    public get _imgURL(): any {
        return this.category;
    }
    public set _imgURL(imgURL: any) {
        this.imgURL = imgURL;
    }

}
