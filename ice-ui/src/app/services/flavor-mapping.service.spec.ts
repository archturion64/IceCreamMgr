import { FlavorCategory } from './../models/flavor-category';
import { TestBed } from '@angular/core/testing';
import { Flavor } from '../models/flavor';


import { FlavorMappingService } from './flavor-mapping.service';

describe('FlavorMappingService', () => {
  let sut: FlavorMappingService;

  const jsObj: any = {
    category: "Sahne-Eis",
    foodIntolerance: "Laktoseintoleranz",
    ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
    name: "Bourbon Vanille",
    nutritionalValue: 208,
    price: "3.65"
  };
const tsObj: Flavor = 
  {
    category: FlavorCategory.CREAM_BASED,
    foodIntolerance: "Laktoseintoleranz",
    ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
    name: "Bourbon Vanille",
    nutritionalValue: 208,
    price: "3.65"
  } as Flavor;

  const jsObjError: any = {
    category: "Sahne-Tee", // error
    foodIntolerance: "Laktoseintoleranz",
    ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
    name: "Bourbon Vanille",
    nutritionalValue: 208,
    price: "3.65"
  };
const tsObjError: Flavor = 
  {
    category: 2000 as FlavorCategory, // error
    foodIntolerance: "Laktoseintoleranz",
    ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
    name: "Bourbon Vanille",
    nutritionalValue: 208,
    price: "3.65"
  } as Flavor;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    sut = TestBed.inject(FlavorMappingService);
  });

  it('should be created', () => {
    expect(sut).toBeTruthy();
  });

  it('should successfully convert to Flavor via fromApi()', () => {
    const actual = sut.fromApi(jsObj);
    expect(actual).toEqual(tsObj);
  });

  it('should successfully convert to any via toApi()', () => {
    const actual = sut.toApi(tsObj);
    expect(actual).toEqual(jsObj);
  });

  it('should fail to convert to Flavor via fromApi() with unknown category', () => {
    expect(() => sut.fromApi(jsObjError)).toThrow(new Error('Unsupported string -> enum conversion of Sahne-Tee detected.'));
  });

  it('should fail to convert to any via toApi() with unknown category', () => {
    expect(() => sut.toApi(tsObjError)).toThrow(new Error('Unsupported enum -> string conversion of 2000 detected.'));
  });

});
