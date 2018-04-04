from django.contrib import admin
from users.models import UserData
# Register your models here.
class UserDataAdmin(admin.ModelAdmin):
    list_display = ["name","created","modified"]
    search_fields = ["name"]


admin.site.register(UserData, UserDataAdmin)