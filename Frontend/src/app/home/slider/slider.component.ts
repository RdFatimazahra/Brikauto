import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';


interface Slide {
  title: string;
  subtitle: string;
  imageUrl: string;
}

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit, OnDestroy{
  slides: Slide[] = [
    {
      title: 'Find the Right Part for Your Vehicle',
      subtitle: 'Quality auto parts at competitive prices',
      // imageUrl:'assets/auto.webp'
      imageUrl:'https://images.pexels.com/photos/28379404/pexels-photo-28379404/free-photo-of-vintage-1966-blue-chevrolet-caprice-classic-car-outdoors.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'
        // imageUrl:'https://images.pexels.com/photos/7529435/pexels-photo-7529435.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'
    },
    // {
    //   title: 'Upgrade Your Performance',
    //   subtitle: 'Boost your car\'s potential with our parts',
    //   imageUrl:'assets/67354.jpg'
    // },
    // {
    //   title: 'Restore Your Classic',
    //   subtitle: 'Authentic parts for vintage vehicles',
    //   imageUrl:'assets/67354.jpg'
    // }
  ];

  currentSlide = 0;
  private intervalId: any;

  constructor(private router: Router) {}

  ngOnInit() {
    this.startSlideShow();
    
  }

  ngOnDestroy() {
    this.stopSlideShow();
  }

  startSlideShow() {
    this.intervalId = setInterval(() => {
      this.nextSlide();
    }, 5000);
  }

  stopSlideShow() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }

  prevSlide() {
    this.currentSlide = (this.currentSlide - 1 + this.slides.length) % this.slides.length;
  }

  onSearchClick() {
    this.router.navigate(['/show']);
  }
}


