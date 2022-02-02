import { FlavorCategory } from './../../models/flavor-category';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Flavor } from 'src/app/models/flavor';
import { ErrorService } from 'src/app/services/error.service';
import { FlavorMappingService } from 'src/app/services/flavor-mapping.service';
import { FlavorsService } from '../../services/flavors.service'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list-flavor',
  templateUrl: './list-flavor.component.html',
  styleUrls: ['./list-flavor.component.css']
})
export class ListFlavorComponent implements OnInit {

  public categoryToName(category: FlavorCategory): string | undefined {
    return FlavorMappingService.supportedCategories.get(category);
  }

  constructor(private route: ActivatedRoute) { }

  flavors: Flavor[] = [];

  ngOnInit(): void {
    this.flavors = this.route.snapshot.data['flavorsData'];
  }

}
