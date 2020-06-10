import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewallCustomersComponent } from './viewall-customers.component';

describe('ViewallCustomersComponent', () => {
  let component: ViewallCustomersComponent;
  let fixture: ComponentFixture<ViewallCustomersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewallCustomersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewallCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
