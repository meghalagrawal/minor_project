from django.shortcuts import render
from keys import KEY_RESPONSE_SUCCESS,KEY_RESPONSE_MESSAGE,KEY_JWT_ACCESS_TOKEN,KEY_ACCESS_TOKEN_ENCRYPTION,KEY_JWT_ACCESS_TOKEN
# Create your views here.
import keys
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def login(request):
	response = {}
	if request.method == 'POST':
		try:
			email = request.POST.get("email")
			password = request.POST.get("password")
			user_instance = UserData.objects.filter(email=email, password=password)
			if user_instance.exists():
				response[keys.KEY_RESPONSE_SUCCESS] = True
				response[keys.KEY_RESPONSE_MESSAGE] = "Successful"
				access_token = jwt.encode({keys.KEY_JWT_ACCESS_TOKEN: email}, keys.KEY_ACCESS_TOKEN_ENCRYPTION,algorithm='HS256')
				response[keys.KEY_RESPONSE_ACCESS_TOKEN] = access_token
			else:
				response[keys.KEY_RESPONSE_SUCCESS] = False
				response[keys.KEY_RESPONSE_MESSAGE] = "Email and password does not match"
		except Exception as e:
			response[keys.KEY_RESPONSE_SUCCESS] = False
			response[keys.KEY_RESPONSE_MESSAGE] = "Invalid Credential. Please try again."
			print(str(e))
	else:
		response[keys.KEY_RESPONSE_SUCCESS] = False
		response[keys.KEY_RESPONSE_MESSAGE] = "Illegal Request!"
		return JsonResponse(response)