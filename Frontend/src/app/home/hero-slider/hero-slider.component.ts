import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

interface Slide {
  title: string;
  subtitle: string;
}

@Component({
  selector: 'app-hero-slider',
  templateUrl: './hero-slider.component.html',
  styleUrls: ['./hero-slider.component.scss']
})
export class HeroSliderComponent implements OnInit, OnDestroy {
  slides: Slide[] = [
    {
      title: 'Find the Right Part for Your Vehicle',
      subtitle: 'Quality auto parts at competitive prices'
    },
    {
      title: 'Upgrade Your Performance',
      subtitle: 'Boost your car\'s potential with our parts'
    },
    {
      title: 'Restore Your Classic',
      subtitle: 'Authentic parts for vintage vehicles'
    }
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