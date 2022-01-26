import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { ErrorService } from './error.service';
import { FlavorMappingService } from './flavor-mapping.service';
import { Flavor } from '../models/flavor';

import { FlavorsService } from './flavors.service';
import { of, throwError } from 'rxjs';
import { FlavorCategory } from '../models/flavor-category';
import { environment } from 'src/environments/environment.prod';

describe('FlavorsService', () => {

  let httpClientMock: jasmine.SpyObj<HttpClient>;
  let flavorMappingServiceMock: jasmine.SpyObj<FlavorMappingService>;
  let errorServiceMock: jasmine.SpyObj<ErrorService>;
  let sut: FlavorsService;


  beforeEach(() => {
    httpClientMock = jasmine.createSpyObj('HttpClient', ['get', 'put']);
    flavorMappingServiceMock = jasmine.createSpyObj('FlavorMappingService', ['fromApi', 'toApi']);
    errorServiceMock = jasmine.createSpyObj('ErrorService', ['handleError', 'handleConnectionError']);
    sut = new FlavorsService(httpClientMock, flavorMappingServiceMock, errorServiceMock);
  });

  it('should be created', () => {
    expect(sut).toBeTruthy();
  });

  it('should call GET with mapped data once', (done: DoneFn) => {

    const response: any[] = [
      {
        category: "Sahne-Eis",
        foodIntolerance: "Laktoseintoleranz",
        ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
        name: "Bourbon Vanille",
        nutritionalValue: 208,
        price: "3.65"
      }
    ];
    const convertedResponce: Flavor[] = [
      {
        category: FlavorCategory.CREAM_BASED,
        foodIntolerance: "Laktoseintoleranz",
        ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
        name: "Bourbon Vanille",
        nutritionalValue: 208,
        price: "3.65"
      } as Flavor
    ];

    httpClientMock.get.and.returnValue(of(response))
    flavorMappingServiceMock.fromApi.and.returnValue(convertedResponce[0]);

    sut.getFlavors()
      .then((actual: Flavor[]) => {
        expect(actual).toEqual(convertedResponce, 'expected response');
        done();
      })
      .catch( (_) =>  fail());

    expect(httpClientMock.get).toHaveBeenCalled();
  });

  it('should call PUT and return mapped data once', (done: DoneFn) => {

    const convertedRequest: any = {
        category: "Sahne-Eis",
        foodIntolerance: "Laktoseintoleranz",
        ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
        name: "Bourbon Vanille",
        nutritionalValue: 208,
        price: "3.65"
      };
    const request: Flavor = 
      {
        category: FlavorCategory.CREAM_BASED,
        foodIntolerance: "Laktoseintoleranz",
        ingredients: ["Kokosfett", "Vanilleschoten", "Zucker", "Milch", "Schlagsahne", "Bourbon-Vanilleextrakt"],
        name: "Bourbon Vanille",
        nutritionalValue: 208,
        price: "3.65"
      } as Flavor;
    httpClientMock.put.and.returnValue(of('created'))
    flavorMappingServiceMock.toApi.and.returnValue(convertedRequest);

    sut.addFlavor(request)
      .then((_) => done())
      .catch((_) => fail());

    expect(httpClientMock.put).toHaveBeenCalled();
  });

  // TODO investigate failure
  xit('should report an error if GET returns an error response', (done: DoneFn) => {

    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404,
      statusText: 'Not Found'
    });

    httpClientMock.get.and.returnValue(throwError(errorResponse));
    sut.getFlavors()
      .then((_) => {
        expect(errorResponse).toEqual(jasmine.objectContaining({
          request: 'GET ' + environment.SERVICE_URL + '/ice-cream/flavors'
        }))
        done();
      })
      .catch((_error) => fail());

      expect(httpClientMock.get).toHaveBeenCalled();
  });


});
