"""MajorProjectServerEnd URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from django.conf import settings
from django.conf.urls.static import static
from onboarding.views import splash_screen,welcome
from users.views import login
from classes.views import classes
from assignments.views import assignments,submissions
from assignments.views import compiler,submit_assignment

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^splash_screen/$', splash_screen),
    url(r'^welcome/$', welcome),
    url(r'^login/$', login),
    url(r'^classes/list/$', classes),
    url(r'^classes/add/$', classes),
    url(r'^assignments/list/$', assignments),
    url(r'^submissions/list/$', submissions),
    url(r'^compiler/$', compiler),
    url(r'^submit_assignment/$', submit_assignment),




]
urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
# urlpatterns += url(r'^.*$', index),