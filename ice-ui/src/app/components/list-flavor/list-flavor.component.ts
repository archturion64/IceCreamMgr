import { FlavorCategory } from './../../models/flavor-category';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Flavor } from 'src/app/models/flavor';
import { ErrorService } from 'src/app/services/error.service';
import { FlavorMappingService } from 'src/app/services/flavor-mapping.service';
import { FlavorsService } from '../../services/flavors.service'

@Component({
  selector: 'app-list-flavor',
  templateUrl: './list-flavor.component.html',
  styleUrls: ['./list-flavor.component.css']
})
export class ListFlavorComponent implements OnInit {

  public categoryToName(category: FlavorCategory): string | undefined {
    return FlavorMappingService.supportedCategories.get(category);
  }

  constructor(  private flavorService: FlavorsService,
                private errorService: ErrorService ) { }

  flavors: Flavor[] = [];

  async ngOnInit(): Promise<void> {
    await this.flavorService.getFlavors()
    .then( (data) => {
      console.log(data);
      this.flavors = data;
    })
    .catch( (error: HttpErrorResponse) => {
      this.errorService.handleConnectionError(error)
    });
  }

}
