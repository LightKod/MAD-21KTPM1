package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.zalo.CreateOrder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener

class PaymentPreviewFragment : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var voucherBtn: Button
    private lateinit var couponBtn: Button
    private lateinit var paymentButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payment_preview, container, false)

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX)

        init(view)

        return view
    }

    private fun init(view: View){
        backBtn = view.findViewById(R.id.backBtn)
        menuBtn = view.findViewById(R.id.menuBtn)

        backBtn.setOnClickListener {
            (this.activity as? MainActivity)?.goBack()
        }

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        // VOUCHER
        voucherBtn = view.findViewById(R.id.voucherBtn)
        couponBtn = view.findViewById(R.id.couponBtn)

        voucherBtn.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(AddVoucherFragment(), "add_voucher")
        }
        couponBtn.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(AddVoucherFragment(), "add_voucher")
        }
        paymentButton = view.findViewById(R.id.continueBtn)
        paymentButton.setOnClickListener {
            // Thực hiện hoạt động mạng trong một coroutine
            CoroutineScope(Dispatchers.IO).launch {
                val orderApi = CreateOrder()

                try {
                    val data =  orderApi.createOrder("500000")
                    if(data!=null)
                    {
                        Log.d("Amount", "500000")
                        Log.d("dataZalo",  data.toString())

                    }

                    val code = data.getString("return_code")
                    val subCode = data.getString("sub_return_code")

                    Log.d("codeZalo", code)
                    Log.d("codeZalo", subCode)


                    if (code == "1") {
                        val token = data.getString("zp_trans_token")
                        Log.d("tokenZalo", token)

                        // Khởi tạo và thanh toán đơn hàng
                        ZaloPaySDK.getInstance().payOrder(
                            requireActivity(), // Activity hiện tại
                            token!!, // Token của ứng dụng
                            "<MerchantApp Deeplink>", // Deeplink của ứng dụng Merchant
                            object : PayOrderListener {
                                override fun onPaymentCanceled(zpTransToken: String?, appTransID: String?) {
                                    // Xử lý khi người dùng hủy thanh toán
                                    Log.d("ZaloPay", "Payment canceled")
                                }

                                override fun onPaymentError(zaloPayErrorCode: ZaloPayError?, zpTransToken: String?, appTransID: String?) {
                                    // Xử lý khi có lỗi xảy ra trong quá trình thanh toán
                                    // Redirect to Zalo/ZaloPay Store when zaloPayError == ZaloPayError.PAYMENT_APP_NOT_FOUND
                                    Log.d("ZaloPay", "Payment error: ${zaloPayErrorCode?.name}")
                                }

                                override fun onPaymentSucceeded(transactionId: String, transToken: String, appTransID: String?) {
                                    // Xử lý khi thanh toán thành công
                                    Log.d("ZaloPay", "Payment succeeded: transactionId=$transactionId, transToken=$transToken, appTransID=$appTransID")
                                }
                            }
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("fetchData", "Error occurred: ${e}")
                }
            }
        }
    }
}