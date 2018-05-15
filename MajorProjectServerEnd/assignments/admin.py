from django.contrib import admin
from assignments.models import AssignmentData, AssignmentSubmissionData, AssignmentTestCaseData

# Register your models here.
class AssignmentDataAdmin(admin.ModelAdmin):
    list_display = ["title","due_date"]
    search_fields = ["title"]


admin.site.register(AssignmentData, AssignmentDataAdmin)

class AssignmentSubmissionDataAdmin(admin.ModelAdmin):
    list_display = ["assignment_instance","user_instance","time_taken"]
    # search_fields = ["title"]


admin.site.register(AssignmentSubmissionData, AssignmentSubmissionDataAdmin)

class AssignmentTestCaseDataAdmin(admin.ModelAdmin):
    list_display = ["assignment_instance","case_input","case_output"]
    # search_fields = ["title"]


admin.site.register(AssignmentTestCaseData, AssignmentTestCaseDataAdmin)