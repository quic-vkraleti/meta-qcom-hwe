DESCRIPTION = "Recipe to install firmware files at lib/firmware on rootfs"
LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

COMPATIBLE_MACHINE = "qcm6490|qcs9100"

SRC_URI ="https://${FW_ARTIFACTORY}/${FW_BUILD_ID}/${FW_BIN_PATH}/${HLOSFIRMWARE}.zip;name=${PBT_ARCH}"

SRC_URI[qcm6490.sha256sum] = "b84431edd9b75a8fa4ca3ec55786c1a12becb0f51d4a7ada14d15c6d8074d2f2"
SRC_URI[qcs9100.sha256sum] = "ef906eab241e742b8fd8c781f9c9dd8d8cbc0677d52ed880a009c950e7d82f81"

include firmware-common.inc

MATCHED_MACHINE = "${@get_matching_machine(d)}"
include firmware-${MATCHED_MACHINE}.inc

HLOSFIRMWARE:qcm6490 = "QCM6490_fw"
HLOSFIRMWARE:qcs9100 = "QCS9100_fw"

HLOSFIRMWARE_PATH = "${S}/${HLOSFIRMWARE}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

python do_install() {

    fw_file = d.getVar("HLOSFIRMWARE")
    fw_path = d.getVar("HLOSFIRMWARE_PATH")

    firmware_install(d, fw_file, fw_path)

    import os
    import shutil

    # Remove files in /usr/lib/dsp, as the same are provided by dspso fw.
    dsp_fwpath = os.path.join(d.getVar('D'), 'usr/lib/dsp')
    if os.path.exists(dsp_fwpath) and os.path.isdir(dsp_fwpath):
        shutil.rmtree(dsp_fwpath)

}

inherit deploy

do_deploy() {
    install -D -m644 ${D}${nonarch_base_libdir}/firmware/qcom/${MATCHED_MACHINE}/Ver_Info.txt ${DEPLOYDIR}
}
addtask deploy before do_build after do_install

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${SOC_ARCH}"

PACKAGES += "${PN}-copyright"

FILES:${PN} += "${nonarch_base_libdir}/* /usr/*"
FILES:${PN}-copyright += "/Qualcomm-Technologies-Inc.-Proprietary"

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN} += "arch"
