import { FlavorCategory } from "./flavor-category";

export interface Flavor {
    name: string,
    category: FlavorCategory,
    ingredients: string[],
    foodIntolerance: string,
    nutritionalValue: number,
    price: string
}