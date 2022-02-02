import { FlavorMappingService } from './flavor-mapping.service';
import { ErrorService } from './error.service';
import { environment } from './../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Flavor } from '../models/flavor';
import { firstValueFrom, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlavorsService {

  constructor(  private http: HttpClient,
                private flavorMappingService: FlavorMappingService,
                private errorService: ErrorService) { }

  private readonly url = environment.SERVICE_URL + '/ice-cream/flavors';


  public getFlavors(): Observable<Flavor[]> {
    return this.http.get<Flavor[]>(this.url);
  }

  public async addFlavor(flavor: Flavor): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders( {
        'Content-Type': 'application/json'
      })
    };

    console.log(JSON.stringify(this.flavorMappingService.toApi(flavor)))

    try {
      return firstValueFrom(this.http.put( this.url,
                                    JSON.stringify(this.flavorMappingService.toApi(flavor)),
                                    httpOptions));
    } catch (error) {
      Object.assign({request: `PUT ${this.url}`}, error);
      return this.errorService.handleError(this.constructor.name, error);
    }
  }
}
