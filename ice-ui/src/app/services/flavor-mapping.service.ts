import { FlavorCategory } from './../models/flavor-category';
import { Injectable } from '@angular/core';
import { Flavor } from '../models/flavor';

@Injectable({
  providedIn: 'root'
})
export class FlavorMappingService {

  constructor() { }

  private categoryToString(category: FlavorCategory): string {
    const result: string | undefined = FlavorMappingService.supportedCategories.get(category);
    if (!result) {
      throw new Error('Unsupported enum -> string conversion of ' + category + ' detected.')
    }
    return result;
  }

  public static readonly supportedCategories: Map<FlavorCategory, string> = new Map ([
    [FlavorCategory.CREAM_BASED, 'Sahne-Eis'],
    [FlavorCategory.WATER_BASED, 'Wasser-Eis'],
    [FlavorCategory.FRUIT_BASED, 'Frucht-Eis']
  ]);

  private stringToCategory(category: string): FlavorCategory {
    const result: [FlavorCategory, string] | undefined = [...FlavorMappingService.supportedCategories]
      .find(([key, val]) => val == category);

    if (!result) {
      throw new Error('Unsupported string -> enum conversion of ' + category + ' detected.')
    }
    return result[0];
  }

  fromApi(obj: any): Flavor {
    const flavor = Object.assign({}, obj);
    flavor.category = this.stringToCategory(obj.category);
    return flavor;
  }

  toApi(flavor: Flavor): any {
    const obj = Object.assign({}, flavor) as any;
    obj.category = this.categoryToString(flavor.category);
    return obj;
  }
}
