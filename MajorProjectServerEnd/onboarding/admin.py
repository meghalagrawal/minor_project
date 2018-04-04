from django.contrib import admin
from onboarding.views import KeysData, WelcomeData

# Register your models here.
class KeysDataAdmin(admin.ModelAdmin):
    list_display = ["key","value","created","modified"]
    # search_fields = ["name"]


admin.site.register(KeysData, KeysDataAdmin)


class WelcomeDataAdmin(admin.ModelAdmin):
    list_display = ["image","quote","created","modified"]
    # search_fields = ["name"]


admin.site.register(WelcomeData, WelcomeDataAdmin)